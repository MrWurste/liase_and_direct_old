import React, { Component } from 'react';

class Login extends Component {
    //contructor,method,accesor,property
    //loginUser(credentials) -> POST to backend (email, password)
    //response from loginUser <- Token
    constructor(props) {
        super(props);
        this.state = {
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
        Event.preventDefault();
    }
    
    logIn(credentials) {
        return fetch("http://localhost:8080/login/authenticate", {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(credentials),
            redirect: 'follow',
            })
            .then(response => response.json)
        }
        async handleSubmit() {
            const response = await this.logIn(this.state);
            if('accessToken' in response) {
                localStorage.setItem('accesstoken', response['accessToken']);
            }
        }

  render() {
    return (
        <div className="loginWrapper">
            <h1>Please log in</h1>
      <form noValidate onSubmit={this.handleSubmit}>
        <label>
            <p>Email</p>
            <input type="text" name="email" placeholder='email' value={this.state.email} onChange={this.handleInput} />
        </label>
        <label>
            <p>Password</p>
            <input type="password" name="password" placeholder='password' value={this.state.password} onChange={this.handleInput} />
        </label>
        <div>
            <button type="submit">Submit</button>
        </div>
      </form>
      </div>
    )
  }
}

export default Login;
