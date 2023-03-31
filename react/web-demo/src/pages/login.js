import React, { useState } from 'react'
import logo from '../logo.svg';
import axios from "axios";

const loginAxios = axios.create({
    baseURL: "https://localhost:20000/login",
    headers: {
      "Content-type": "application/json"
    },
    method: 'POST'
 });

const loginAction = async (username, password) => {
    try {
        const response = await loginAxios.request({ data: { 'username': username, 'password':password} });
    }
    catch(error){
        if(error.response && error.response.status === 400) {
            console.log(error.response.data)
            return Promise.reject(error.response.data.error)
        } else if(error.request) {
            console.log('Request error: ', error.request)
        } else {
            console.log(error.response)
        }
    }
}

const LoginPage = () => {
    const [username, setUsername] = useState('thomasli')
    const [password, setPassword] = useState('password')

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <input type="text" value={username} onChange={ text => setUsername(text)}></input><br/>
                <input type="password" value={password} onChange={ text => setPassword(text)}></input><br/>
                <input type="button" value="submit" onClick={ () => loginAction(username , password)}></input>
            </header>
        </div>
    );
}

export { LoginPage }