import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';

// Task 3: Interface exported so CourseListComponent can import it
export interface Course {
  id: number;
  name: string;
  code: string;
  credits: number;
}

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css'
})
export class CourseCardComponent implements OnChanges {
  // Task 3: @Input property
  @Input() course!: Course;

  // Task 3: @Output EventEmitter with generic payload type
  @Output() enrollRequested = new EventEmitter<number>();

  // Task 2: ngOnChanges Lifecycle Hook
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      console.log('CourseCardComponent ngOnChanges:', {
        previousValue: changes['course'].previousValue,
        currentValue: changes['course'].currentValue
      });
    }
  }

  // Task 3: Trigger event emission
  onEnroll(): void {
    this.enrollRequested.emit(this.course.id);
  }
}