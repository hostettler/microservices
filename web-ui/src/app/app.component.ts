import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements OnInit {
  title = 'web-ui';
  private gridApi;
  private gridColumnApi;

      columnDefs = [
        {headerName: 'LEI', field: 'lei',
        width: 200,
        minWidth: 200,
        maxWidth: 200,
        cellStyle: {'text-align': 'left'}},
        {headerName: 'Name', field: 'name', cellStyle: {'text-align': 'left'}},
        {headerName: 'Status', field: 'status',
        width: 200,
        minWidth: 200,
        maxWidth: 200
    }
    ];

    rowData = [ ];

    ngOnInit() {
     fetch('http://localhost:8080/counterparties')
      .then(result => result.json())
      .then(rowData => this.rowData = rowData);
  }

    onGridReady(params) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;


    const allColumnIds = [];
    this.gridColumnApi.getAllColumns().forEach(function(column) {
      allColumnIds.push(column.colId);
    });
    this.gridColumnApi.autoSizeColumns(allColumnIds);
    this.gridApi.sizeColumnsToFit();
  }
}
