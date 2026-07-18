import React, { useContext } from 'react';
import ThemeContext from './ThemeContext'; // Import context token

export default function EmployeeCard({ employee }) {
  // Retrieve target value instantly with useContext, removing prop drilling completely
  const theme = useContext(ThemeContext);

  return (
    <div className="employee-card">
      <h3>{employee.name}</h3>
      <p><strong>Role:</strong> {employee.role}</p>
      <p><strong>Department:</strong> {employee.dept}</p>
      
      <div className="card-actions">
        {/* Pass custom theme context layout identifiers straight to classes */}
        <button className={`btn-theme-${theme}`}>View Profile</button>
        <button className={`btn-theme-${theme}`}>Edit</button>
      </div>
    </div>
  );
}