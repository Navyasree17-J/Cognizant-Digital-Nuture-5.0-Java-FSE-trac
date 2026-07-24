import { Component } from '@angular/core';
import { CourseCardComponent, Course } from '../../components/course-card/course-card.component';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CourseCardComponent],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent {
  // Task 3: Array with 5 course objects
  courses: Course[] = [
    { id: 101, name: 'Angular Basics', code: 'CS101', credits: 3 },
    { id: 102, name: 'Data Structures', code: 'CS102', credits: 4 },
    { id: 103, name: 'Web Development', code: 'CS103', credits: 3 },
    { id: 104, name: 'Database Management', code: 'CS104', credits: 3 },
    { id: 105, name: 'Cloud Computing', code: 'CS105', credits: 2 }
  ];

  // Task 3: Selected course ID tracker
  selectedCourseId: number | null = null;

  // Task 3: Event handler for child event
  onEnroll(courseId: number): void {
    console.log('Enrolling in course: ' + courseId);
    this.selectedCourseId = courseId;
  }
}