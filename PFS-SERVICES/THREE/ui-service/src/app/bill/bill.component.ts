import { Component, OnInit } from '@angular/core';
import { SharedDataService } from '../Payment-Service/shared-data.service';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {
  invoiceData: any;
  isDetailsVisible: boolean = false;
  currentDateTime: Date = new Date();

  constructor(private sharedDataService: SharedDataService) { }

  ngOnInit() {
    const storedInvoiceData = localStorage.getItem('invoiceData');
    if (storedInvoiceData) {
      this.invoiceData = JSON.parse(storedInvoiceData);
    }
  }

  toggleDetails() {
    this.isDetailsVisible = !this.isDetailsVisible;
  }
}
