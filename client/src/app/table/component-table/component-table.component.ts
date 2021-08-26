import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {DataService} from "../../service/data.service";
import {Components} from "../../models/Component";
import {DialogBoxComponent} from "../dialog-box/dialog-box.component";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";

@Component({
  selector: 'app-component-table',
  templateUrl: './component-table.component.html',
  styleUrls: ['./component-table.component.scss']
})
export class ComponentTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'type', 'label', 'action'];

  dataSource!: MatTableDataSource<Components>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  constructor(private dataService: DataService, private dialog: MatDialog, private router: Router) {
    this.dataService.getAllComponents().subscribe(comp => {
      this.dataSource = new MatTableDataSource<Components>(comp);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

  ngOnInit(): void {
  }

  applyFilter($event: KeyboardEvent) {
    const filterValue = ($event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  openDialog(obj: any) {
    obj.action = 'Delete';
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '250px',
      data:obj
    });

    dialogRef.afterClosed().subscribe(result => {
      this.deleteRowData(result.data);
    });
  }

  updateRowData(comp: any){
    this.router.navigate(['/screen'], {queryParams: {id: comp.id, type: 'component'}})
  }

  deleteRowData(row_obj: any){
    this.dataService.deleteComponent(row_obj.id).subscribe(()=>{
      window.location.reload();
    })
  }

}
