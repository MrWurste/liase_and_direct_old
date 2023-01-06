import React, { Component } from "react";

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            email: '',
            password: '',
        };
        this.handleInput = this.handleInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleInput(event) {
        const name = event.target.name;
        const newState = {};
        newState[name] = event.target.value;
        this.setState(newState);
        event.preventDefault();
    }
    registerUser(credentials) {
        return fetch("http://localhost:8080/login/register", {
            method: 'POST',
            headers: { "Content-Type":"application/json" },
            body: JSON.stringify(credentials),
            redirect: 'follow',
        })
        .then(response => response.json)
    }
    async handleSubmit() {
        const response = await this.registerUser(this.state)
    }
    
    render() {
        return (
            <div className="registerWrapper">
                <h1>Please create new account</h1>
                <form noValidate onSubmit={this.handleSubmit}>
                    <label>
                        <p>Username</p>
                        <input type="text" name="username" placeholder="username" value={this.state.username} onChange={this.handleInput} />
                    </label>
                    <label>
                        <p>Email</p>
                        <input type="email" name="email" placeholder="email" value={this.state.email} onChange={this.handleInput} />
                    </label>
                    <label>
                        <p>Password</p>
                        <input type="password" name="password" placehodler="password" value={this.state.password} onChange={this.handleInput} />
                    </label>
                    <div>
                        <button type="submit">Register</button>
                    </div>
                </form>
            </div>
        )
    }
}

export default Register;