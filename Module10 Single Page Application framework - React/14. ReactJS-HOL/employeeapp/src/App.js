import React, { useState } from 'react';
import './App.css';
import ThemeContext from './ThemeContext'; // Import the context engine[cite: 8]
import EmployeesList from './EmployeesList';

function App() {
  const [theme, setTheme] = useState('light');

  const mockEmployees = [
    { id: 1, name: "Alexander Wright", role: "Principal Architect", dept: "Engineering" },
    { id: 2, name: "Sarah Jenkins", role: "Quality Assurance Lead", dept: "Operations" },
    { id: 3, name: "Michael Chang", role: "Senior Full Stack Dev", dept: "Apps Centric Solutions" }
  ];

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === 'light' ? 'dark' : 'light'));
  };

  // Dynamically sync body backdrop presentation style container classes
  document.body.className = `app-theme-${theme}`;

  return (
    // Define the Context Provider to wrap the application tree, passing down states[cite: 8]
    <ThemeContext.Provider value={theme}>
      <div className="app-container">
        
        <header className="dashboard-header">
          <div>
            <h1>Employee Core Hub</h1>
            <p style={{ margin: 0, opacity: 0.7 }}>Context API Provider Engine Enabled</p>
          </div>
          <button className={`btn-toggle btn-theme-${theme}`} onClick={toggleTheme}>
            Switch to {theme === 'light' ? 'Dark' : 'Light'} Mode
          </button>
        </header>

        {/* Theme state is no longer passed as a prop down this tree level[cite: 8] */}
        <EmployeesList employees={mockEmployees} />

      </div>
    </ThemeContext.Provider>
  );
}

export default App;