import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ClienteService } from "src/app/services/cliente.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Cliente } from "src/app/interfaces/cliente";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-cliente-editar",
  templateUrl: "./cliente-editar.component.html",
  styleUrls: ["./cliente-editar.component.css"],
})
export class ClienteEditarComponent implements OnInit {
  public formEditarCliente: FormGroup;
  private clientId?: string;
  public nome?: FormControl;
  public telefone?: FormControl;
  public email?: FormControl;

  constructor(private clienteService: ClienteService, private router: Router, private activateRoute: ActivatedRoute) {
    this.formEditarCliente = this.getForm();
    console.log("abc");
  }

  ngOnInit(): void {
    this.activateRoute.queryParams.subscribe((params) => {
      this.clientId = params["id"];
      console.log("Client ID:", this.clientId);
      if (this.clientId) {
        this.loadCliente(this.clientId);
      }
    });
  }

  loadCliente(id: string): void {
    console.log("Loading client with ID:", id); // Add this line
    this.clienteService.getById(id).subscribe({
      next: (cliente: Cliente) => {
        console.log("Loaded client:", cliente); // Add this line
        this.formEditarCliente = this.getForm();
        this.formEditarCliente.patchValue(cliente);
      },
      error: (error: HttpErrorResponse) => {
        console.error("Ocorreu um erro ao tentar carregar o cliente:", error.message);
      },
    });
  }

  private getForm(): FormGroup {
    console.log("Initializing form"); // Add this line
    this.nome = new FormControl("", [Validators.minLength(3), Validators.required]);
    this.telefone = new FormControl("", [Validators.minLength(3), Validators.required]);
    this.email = new FormControl("", [Validators.minLength(3), Validators.required, Validators.email]);
    console.log("Form initialized with controls:", this.nome, this.telefone, this.email); // Add this line

    return new FormGroup({
      nome: this.nome,
      telefone: this.telefone,
      email: this.email,
    });
  }

  onSaveEditarCliente(): void {
    if (this.formEditarCliente.invalid) {
      return;
    }

    const cliente: Partial<Cliente> = {
      id: this.clientId,
      nome: this.formEditarCliente.get("nome")?.value,
      telefone: this.formEditarCliente.get("telefone")?.value,
      email: this.formEditarCliente.get("email")?.value,
    };

    this.clienteService.update(cliente.id!, cliente).subscribe({
      next: () => {
        this.router.navigate(["cliente-lista"]);
      },
      error: (error: HttpErrorResponse) => {
        console.error("Ocorreu um erro ao tentar atualizar o cliente:", error.message);
      },
    });
  }
}
