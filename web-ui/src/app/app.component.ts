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
		{
			headerName: 'LEI', field: 'lei',
			width: 200,
			minWidth: 200,
			maxWidth: 200,
			cellStyle: { 'text-align': 'left' }
		},
		{ headerName: 'Name', field: 'name', cellStyle: { 'text-align': 'left' } },
		{
			headerName: 'Status', field: 'status',
			width: 200,
			minWidth: 200,
			maxWidth: 200
		}
	];

   rowData = [];
   pieChartLabels = ['eee'];
   pieChartData = [212];
   pieChartType:string = 'pie';

	onGridReady(params) {
		this.gridApi = params.api;
		this.gridColumnApi = params.columnApi;


		const allColumnIds = [];

		this.gridColumnApi.autoSizeColumns(allColumnIds);
		this.gridApi.sizeColumnsToFit();
	}

	chartOptions = {
		responsive: true
	};


 
  // events
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
   console.log(e);
   }   

	
	ngOnInit() {
		fetch('http://localhost:8080/counterparties')
			.then(result => result.json())
			.then(rowData => this.rowData = rowData);

		fetch('http://localhost:8100/valuation?currency=USD')
		 	.then(result => result.json())
			.then(objectData => {
				console.log(objectData)
				for(var property in objectData.breakdownByCurrency) {
					console.log(property)
					console.log(objectData.breakdownByCurrency[property]);
					this.pieChartLabels.push(property);
					this.pieChartData.push(objectData.breakdownByCurrency[property]);
					this.p();
				}
			});		
			
	}
	
	
	p() {
	
	}

}
