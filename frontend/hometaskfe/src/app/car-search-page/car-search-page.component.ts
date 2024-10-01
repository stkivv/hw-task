import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-car-search-page',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './car-search-page.component.html',
  styleUrl: './car-search-page.component.scss',
})
export class CarSearchPageComponent {
  constructor(private http: HttpClient) {}

  find = new FormControl('');
  userId = new FormControl('');
  carId = new FormControl('');
  sort = new FormControl('id:asc');

  data: any;

  submitForm() {
    let url = 'http://localhost:8080/';
    let params = new HttpParams();

    if (this.userId.value) {
      this.doGet(url + 'users/' + this.userId.value + '/cars', params);
      return;
    }

    url += 'cars';

    if (this.carId.value) {
      this.doGet(url + '/' + this.carId.value, params);
      return;
    }
    if (this.find.value) {
      params = params.append('find', this.find.value);
    }
    if (this.sort.value) {
      params = params.append('sort', this.sort.value);
    }
    this.doGet(url, params);
  }

  private doGet(url: string, params: HttpParams) {
    this.http.get(url, { params: params }).subscribe({
      next: (response: any) => {
        this.data = response;
      },
      error: (error: any) => {
        this.data = 'Error fetching data!';
        console.error(error);
      },
    });
  }
}

