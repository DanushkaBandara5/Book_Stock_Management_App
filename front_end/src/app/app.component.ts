import { Component } from '@angular/core';
import {Book} from "./dto/book";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'front_end';
  bookList:Array<Book> =[];
  constructor(private http:HttpClient) {
    http.get<Array<Book>>('http://localhost:8080/api/v1/books').
    subscribe(bookList=>this.bookList=bookList);
  }

  saveBook(isbn: HTMLInputElement, title: HTMLInputElement) {
    let isbn1 =isbn.value;
    let title1=title.value;
    if(!isbn.value){
      isbn.select();
    }
    if(!title.value){
      title.select();
    }
    this.http.post('http://localhost:8080/api/v1/books',
      new Book(isbn.value,title.value)).subscribe((data)=>{
        this.bookList.push(new Book(isbn1,title1));
    });
    isbn.value="";
    title.value="";
    isbn.select();



  }

  clearText(isbn: HTMLInputElement, title: HTMLInputElement) {
    isbn.value="";
    title.value="";
    isbn.select();

  }
}
