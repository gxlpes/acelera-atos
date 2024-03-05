import { Component, EventEmitter, OnInit } from "@angular/core";
import { BsModalRef } from "ngx-bootstrap/modal";

@Component({
  selector: "app-confirmation-modal",
  templateUrl: "./confirmation-modal.component.html",
  styleUrls: ["./confirmation-modal.component.css"],
})
export class ConfirmationModalComponent implements OnInit {
  public onClose: EventEmitter<string> = new EventEmitter<string>();
  public id: string | undefined;

  constructor(public modalRef: BsModalRef) {}

  ngOnInit(): void {}

  confirm(): void {
    this.onClose.emit("confirm");
    this.modalRef.hide();
  }
}
