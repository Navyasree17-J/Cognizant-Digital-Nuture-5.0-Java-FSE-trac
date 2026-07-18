import React from 'react';
import EmployeeCard from './EmployeeCard';

// Notice that the theme prop is not explicitly passed down to the child card
export default function EmployeesList({ employees }) {
  return (
    <div className="employee-grid">
      {employees.map((emp) => (
        <EmployeeCard key={emp.id} employee={emp} />
      ))}
    </div>
  );
}