import React, { Component } from "react";
import "./App.css";
import Header from "./Header";
import Home from "./Home";
import Profile from "./Profile";
import Announcments from "./Announcments";
import Login from "./Login";
import Register from "./Register";
import { Routes,Route } from "react-router-dom";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      token: localStorage.getItem('accessToken') //I try just to see this Item, not for "production"
    };
  }

  render() {
    return (
      <div className="App">
        <Header />
        <Routes>
            <Route exact path="/" element={<Home />} />
            <Route exact path="/announcments" element={<Announcments />} />
            <Route exact path="/profile" element={<Profile />} />
            <Route exact path="/login" element={<Login />} />
            <Route exact path="/login/register" element={<Register />} />
        </Routes>
        <input value={this.state.token} />
      </div>
    );
  }
}
export default App;
