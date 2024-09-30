import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { bootstrapApplication } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-search-page',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './user-search-page.component.html',
  styleUrl: './user-search-page.component.scss',
})
export class UserSearchPageComponent {
  constructor(private http: HttpClient) {}

  name = new FormControl('');
  userId = new FormControl('');
  sort = new FormControl('id:asc');

  data: any;

  submitForm() {
    const url = 'http://localhost:8080/users';
    let params = new HttpParams();
    if (this.userId.value) {
      this.doFetch(url + '/' + this.userId.value, params);
      return;
    }
    if (this.name.value) {
      params = params.append('find', this.name.value);
    }
    if (this.sort.value) {
      params = params.append('sort', this.sort.value);
    }
    this.doFetch(url, params);
  }

  private doFetch(url: string, params: HttpParams) {
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

bootstrapApplication(UserSearchPageComponent, {
  providers: [provideHttpClient()],
});
