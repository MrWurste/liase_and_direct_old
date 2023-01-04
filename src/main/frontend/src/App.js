import React, { Component } from "react";
import "./App.css";
import Header from "./Header";
import Home from "./Home";
import Profile from "./Profile";
import Announcments from "./Announcments";
import Login from "./Login";
import { Routes,Route } from "react-router-dom";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      token: 0
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
        </Routes>
      </div>
    );
  }
}
export default App;
