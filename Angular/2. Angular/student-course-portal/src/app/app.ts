import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { CourseListComponent } from './components/course-list/course-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NgIf, HomeComponent, CourseListComponent],
  template: `
    <div style="padding: 20px;">
      <button (click)="toggleHome()">Toggle Home Component (Observe ngOnDestroy)</button>
      <hr />
      
      <app-home *ngIf="showHome"></app-home>
      
      <hr />
      <app-course-list></app-course-list>
    </div>
  `
})
export class AppComponent {
  showHome = true;

  toggleHome(): void {
    this.showHome = !this.showHome;
  }
}