import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ClienteListaComponent } from "./components/cliente-lista/cliente-lista.component";
import { ClienteCadastroComponent } from "./components/cliente-cadastro/cliente-cadastro.component";
import { ClienteEditarComponent } from "./components/cliente-editar/cliente-editar.component";

const routes: Routes = [
  { path: "cliente-lista", component: ClienteListaComponent },
  { path: "cadastro-cliente", component: ClienteCadastroComponent },
  { path: "editar-cliente", component: ClienteEditarComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
