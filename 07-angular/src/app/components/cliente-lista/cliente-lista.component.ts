import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Cliente } from "src/app/interfaces/cliente";
import { ClienteService } from "src/app/services/cliente.service";
import { Router } from "@angular/router";
import { BsModalService, BsModalRef } from "ngx-bootstrap/modal";
import { ConfirmationModalComponent } from "../confirmation-modal/confirmation-modal.component";

@Component({
  selector: "app-cliente-lista",
  templateUrl: "./cliente-lista.component.html",
  styleUrls: ["./cliente-lista.component.css"],
})
export class ClienteListaComponent implements OnInit {
  public clientes: Cliente[] = [];
  public modalRef: BsModalRef | undefined;

  constructor(private clienteService: ClienteService, private router: Router, private modalService: BsModalService) {}

  ngOnInit(): void {
    this.loadCliente();
  }

  loadCliente(): void {
    this.clienteService.getAll().subscribe({
      next: (result: Cliente[]) => {
        this.clientes = result;
      },
      error: (error: HttpErrorResponse) => {
        console.error("Falha ao carregar a lista de clientes", error.message);
      },
    });
  }

  onNovoCliente(): void {
    this.router.navigate(["cadastro-cliente"]);
  }

  onRemoveCliente(id: string): void {
    this.modalRef = this.modalService.show(ConfirmationModalComponent);
    this.modalRef.content.id = id;
    this.modalRef.content.onClose.subscribe((result: string) => {
      if (result === "confirm") {
        this.clienteService.delete(id).subscribe({
          next: () => {
            this.loadCliente();
          },
          error: (error: HttpErrorResponse) => {
            console.error("Ocorreu um erro ao tentar remover o cliente:", error.message);
          },
        });
      }
    });
  }

  onEditCliente(id: string): void {
    this.router.navigate(["editar-cliente"], { queryParams: { id: id } });
  }
}
