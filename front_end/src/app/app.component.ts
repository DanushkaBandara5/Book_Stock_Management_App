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
  indexs:Number =0;
  constructor(private http:HttpClient) {
    http.get<Array<Book>>('http://localhost:8080/api/v1/books').
    subscribe(bookList=>this.bookList=bookList);
  }

  saveBook(isbn: HTMLInputElement, title: HTMLInputElement, quantity: HTMLInputElement, btn: HTMLButtonElement) {
    if(btn.innerText==="Save"){
      let isbn1 =isbn.value;
      let title1=title.value;
      let qty1 :Number=+quantity.value;
      if(!isbn.value){
        isbn.select();
        return;
      }
      if(!title.value){
        title.select();
        return;
      }
      if(!quantity.value){
        quantity.select();
        return;
      }
      this.http.post('http://localhost:8080/api/v1/books',
        new Book(isbn.value,title.value,+quantity.value)).subscribe((data)=>{
        this.bookList.push(new Book(isbn1,title1,qty1));
      });
    }
    else if(btn.innerText==="Edit"){
      this.http.patch<Book>('http://localhost:8080/api/v1/books',new Book(isbn.value,title.value,+quantity.value)).
      subscribe(book=>{
        this.bookList.splice(+this.indexs,1,book);
      })
      btn.innerText="Save";
      isbn.removeAttribute("disabled")
    }

    isbn.value="";
    title.value="";
    quantity.value=""
    isbn.select();



  }

  clearText(isbn: HTMLInputElement, title: HTMLInputElement, quantity: HTMLInputElement, btn: HTMLButtonElement) {
    isbn.value="";
    title.value="";
    quantity.value="";
    isbn.select();
    isbn.removeAttribute("disabled");
    btn.innerText="Save"

  }

  deleteBook(book: Book) {
    this.http.delete(`http://localhost:8080/api/v1/books/${book.isbn}`).subscribe(data=>{
      var index = this.bookList.indexOf(book);
      this.bookList.splice(index,1);
    });
  }

  editBook(book: Book, isbn: HTMLInputElement, title: HTMLInputElement, quantity: HTMLInputElement, btn: HTMLButtonElement) {
    isbn.value=book.isbn;
    title.value=book.title;
    quantity.value=book.qty.toString();
    this.indexs=this.bookList.indexOf(book);
    btn.innerText="Edit";
    isbn.setAttribute("disabled","true")


  }


  searchBook(search: HTMLInputElement) {
    // alert("ok")
    this.http.get<Array<Book>>(`http://localhost:8080/api/v1/books?q=${search.value}`).
    subscribe(bookList=>this.bookList=bookList);
  }
}
