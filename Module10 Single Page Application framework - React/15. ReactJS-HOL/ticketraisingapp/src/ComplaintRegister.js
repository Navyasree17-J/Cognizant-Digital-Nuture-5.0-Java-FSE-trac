import React, { useState } from 'react';

export default function ComplaintRegister() {
  // Controlled component input state management object
  const [formData, setFormData] = useState({
    ename: '',
    complaint: ''
  });

  // Dynamic input controller tracking fields via their 'name' attributes
  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value // Computed property matching the exact assignment logic
    }));
  };

  // Form Submission Handler[cite: 9]
  const handleSubmit = (event) => {
    event.preventDefault(); // Stop standard browser reloads[cite: 9]

    if (!formData.ename.trim() || !formData.complaint.trim()) {
      alert("Please populate all fields before filing a ticket request.");
      return;
    }

    // Generate a random Reference Transaction ID[cite: 9]
    const NumberHolder = Math.floor(Math.random() * 100) + 1;

    // Build message mapping exactly to the lab criteria alert parameters[cite: 9]
    const msg = 'Thanks ' + formData.ename + '\nYour Complaint was Submitted.\nTransaction ID is: ' + NumberHolder;
    
    alert(msg);

    // Clear form inputs upon successful submission
    setFormData({ ename: '', complaint: '' });
  };

  return (
    <div className="form-card">
      <h2 className="form-title">Register your complaints here!!!</h2>
      
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label className="form-label" htmlFor="ename">Name:</label>
          <input 
            type="text" 
            id="ename"
            name="ename" 
            className="form-input"
            value={formData.ename} 
            onChange={handleChange}
            placeholder="Enter your name"
          />
        </div>

        <div className="form-group">
          <label className="form-label" htmlFor="complaint">Complaint:</label>
          <textarea 
            id="complaint"
            name="complaint" 
            className="form-textarea"
            value={formData.complaint} 
            onChange={handleChange}
            placeholder="Describe the issue you are experiencing..."
          />
        </div>

        <button type="submit" className="btn-submit">Submit</button>
      </form>
    </div>
  );
}