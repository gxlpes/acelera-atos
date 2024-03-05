import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { ClienteCadastroComponent } from "./components/cliente-cadastro/cliente-cadastro.component";
import { ClienteListaComponent } from "./components/cliente-lista/cliente-lista.component";
import { BootstrapModule } from "./modules/bootstrap/bootstrap.module";
import { ConfirmationModalComponent } from './components/confirmation-modal/confirmation-modal.component';
import { ClienteEditarComponent } from './components/cliente-editar/cliente-editar.component';

@NgModule({
  declarations: [AppComponent, ClienteListaComponent, ClienteCadastroComponent, ConfirmationModalComponent, ClienteEditarComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, BrowserAnimationsModule, BootstrapModule, FormsModule, ReactiveFormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
