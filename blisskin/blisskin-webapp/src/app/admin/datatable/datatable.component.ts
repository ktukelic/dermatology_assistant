import {Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-datatable',
  templateUrl: './datatable.component.html',
  styleUrls: ['./datatable.component.scss']
})
export class DatatableComponent implements OnInit {
  dtOptions: DataTables.Settings = {};

  constructor() { }

  ngOnInit(): void {
    this.dtOptions = {
      searching: false,
      paging: true,
      scrollY: '200',
      info: false,
      lengthChange: false
    };
  }

}
