import React, { useState } from 'react';

export default function Register() {
  // Input Values State
  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    password: ''
  });

  // Validation Error Strings Tracking State
  const [errors, setErrors] = useState({
    fullName: '',
    email: '',
    password: ''
  });

  // Verification Helper Flag Function
  const validateForm = (errorObj) => {
    let valid = true;
    Object.values(errorObj).forEach((val) => {
      if (val.length > 0) valid = false;
    });
    return valid;
  };

  // Real-time Event Change Listener Validation Core
  const handleChange = (event) => {
    const { name, value } = event.target;
    
    // Process live values state changes
    setFormData(prev => ({ ...prev, [name]: value }));

    // Local snapshot copy of updates to perform validation on
    let currentErrors = { ...errors };

    // Switch case structure mapped directly from lab requirements
    switch (name) {
      case 'fullName':
        currentErrors.fullName = value.length < 5 
          ? 'Full Name must be 5 characters long!' 
          : '';
        break;

      case 'email':
        // Regular expression verifying both '@' and '.' are present
        const validEmailRegex = RegExp(/^[^\s@]+@[^\s@]+\.[^\s@]+$/);
        currentErrors.email = validEmailRegex.test(value)
          ? ''
          : 'Email is not valid!';
        break;

      case 'password':
        currentErrors.password = value.length < 8
          ? 'Password must be 8 characters long!'
          : '';
        break;

      default:
        break;
    }

    setErrors(currentErrors);
  };

  // Submission Event Handler
  const handleSubmit = (event) => {
    event.preventDefault(); // Stop native page reload routing execution

    // Final defensive validation verification sweep
    if (validateForm(errors) && formData.fullName && formData.email && formData.password) {
      alert('Valid Form');
    } else {
      // Trigger native modular popups sequentially to match expected lab behavior
      if (errors.fullName !== '') {
        alert(errors.fullName);
      } else if (errors.email !== '') {
        alert(errors.email);
      } else if (errors.password !== '') {
        alert(errors.password);
      } else {
        alert('Please complete all form inputs before submitting.');
      }
    }
  };

  return (
    <div className="form-card">
      <h2 className="form-title">Register Here!!!</h2>
      
      <form onSubmit={handleSubmit} noValidate>
        {/* Full Name Input Field */}
        <div className="form-group">
          <label className="form-label">Name:</label>
          <input 
            type="text" 
            name="fullName" 
            className="form-input"
            value={formData.fullName} 
            onChange={handleChange}
            placeholder="Enter full name"
          />
          {errors.fullName && <span className="error-text">{errors.fullName}</span>}
        </div>

        {/* Email Input Field */}
        <div className="form-group">
          <label className="form-label">Email:</label>
          <input 
            type="email" 
            name="email" 
            className="form-input"
            value={formData.email} 
            onChange={handleChange}
            placeholder="Enter email address"
          />
          {errors.email && <span className="error-text">{errors.email}</span>}
        </div>

        {/* Password Input Field */}
        <div className="form-group">
          <label className="form-label">Password:</label>
          <input 
            type="password" 
            name="password" 
            className="form-input"
            value={formData.password} 
            onChange={handleChange}
            placeholder="••••••••"
          />
          {errors.password && <span className="error-text">{errors.password}</span>}
        </div>

        <button type="submit" className="btn-submit">Submit</button>
      </form>
    </div>
  );
}