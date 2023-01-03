import React, { Component } from "react";
import "./App.css";
import Header from "./Header";
import Home from "./Home";
import Profile from "./Profile";
import Announcments from "./Announcments";
import { Routes,Route } from "react-router-dom";

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header />
        <Routes>
            <Route exact path="/" element={<Home />} />
            <Route exact path="/announcments" element={<Announcments />} />
            <Route exact path="/profile" element={<Profile />} />
        </Routes>
      </div>
    );
  }
}
export default App;
