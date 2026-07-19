import React, { Component } from 'react';

export default class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      person: null,
      loading: true
    };
  }

  // Asynchronous lifecycle execution block to pull data from REST API
  async componentDidMount() {
    this.fetchRandomUser();
  }

  // Refactored method block encapsulating the exact requested codebase pattern
  async fetchRandomUser() {
    this.setState({ loading: true });
    try {
      const url = "https://api.randomuser.me/";
      const response = await fetch(url);
      const data = await response.json();
      
      // Update state parameters matching the exact snippet requirements[cite: 11]
      this.setState({ person: data.results[0], loading: false });
      console.log(data.results[0]);
    } catch (error) {
      console.error("Failed to retrieve user data profile payload details:", error);
      this.setState({ loading: false });
    }
  }

  render() {
    const { person, loading } = this.state;

    // Loading State Conditional Presentation
    if (loading) {
      return (
        <div className="loading-box">
          <div className="spinner"></div>
          <p>Retrieving API Profile Identity Records...</p>
        </div>
      );
    }

    if (!person) {
      return <div className="loading-box">Error mapping active user response logs.</div>;
    }

    // Extract title, first name, and picture attributes safely from API object data structures[cite: 11]
    const { title, first, last } = person.name;
    const imageUrl = person.picture.large;

    return (
      <div className="profile-card">
        {/* Render Identity Image Avatar Visuals[cite: 11] */}
        <img 
          src={imageUrl} 
          alt={`${first}'s portrait`} 
          className="avatar-frame" 
        />
        
        {/* Display title and firstname dynamically[cite: 11] */}
        <h2 className="user-title">
          {title} {first} {last}
        </h2>
        
        <span className="user-badge">Verified REST Profile</span>
        
        <button 
          className="btn-refresh" 
          onClick={() => this.fetchRandomUser()}
        >
          Load Next User
        </button>
      </div>
    );
  }
}