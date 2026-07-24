import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, OnDestroy {
  // Task 1: Interpolation
  portalName: string = 'Student Course Portal';

  // Task 1: Property Binding
  isPortalActive: boolean = true;

  // Task 1: Event Binding
  message: string = '';

  // Task 1: Two-Way Binding
  searchTerm: string = '';

  // Task 2: ngOnInit Lifecycle Hook
  ngOnInit(): void {
    console.log('HomeComponent initialised — courses loaded');
  }

  // Task 2: ngOnDestroy Lifecycle Hook
  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  // Task 1: Event handler
  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }
}