import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DataService} from "../../service/data.service";
import {MatTableDataSource} from "@angular/material/table";
import {Screen} from "../../models/Screen";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";

@Component({
  selector: 'app-screen-table',
  templateUrl: './screen-table.component.html',
  styleUrls: ['./screen-table.component.scss']
})
export class ScreenTableComponent implements OnInit {

  displayedColumns: string[] = ['id', 'type', 'header', 'idcomp', 'action'];

  dataSource!: MatTableDataSource<Screen>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  constructor(private dataService: DataService, private dialog: MatDialog, private router: Router) {
    this.dataService.getAllScreens().subscribe(scr => {
      this.dataSource = new MatTableDataSource<Screen>(scr);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
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

  updateRowData(scr: any){
    this.router.navigate(['/screen'], {queryParams: {id: scr.id}})
  }

  deleteRowData(row_obj: any){
    this.dataService.deleteScreen(row_obj.id).subscribe(()=> {
      window.location.reload();
      });
  }

}
