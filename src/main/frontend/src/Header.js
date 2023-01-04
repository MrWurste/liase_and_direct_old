import React, { Component } from "react";
import { Link } from "react-router-dom";

class Header extends Component {
  
  render() {
    return (
      <div>
        <h2>Header of header</h2>
        <Link to="/">
          <button>Home</button>
        </Link>
        <Link to="/announcments">
          <button>Announcments</button>
        </Link>
        <Link to="/profile">
          <button>Profile</button>
        </Link>
        <Link to="/login">
          <button>Login</button>
        </Link>
      </div>
    );
  }
}

export default Header;