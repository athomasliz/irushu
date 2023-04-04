import React, { useState } from 'react'
import logo from '../logo.svg';
import axios from "axios";

const loginAxios = axios.create({
    baseURL: "https://localhost:20000",
    headers: {
      "Content-type": "application/json"
    }
 });

 const demoAxios = axios.create({
    baseURL: "https://localhost:20001/demo",
    headers: {
      "Content-type": "application/json"
    }
 });

const loginAction = async (username, password, setToken) => {
    try {
        const response = await loginAxios.post('/login', { 'username': username, 'password':password} );
        let token = response.headers.get('Authorization').replace('Bearer ', '');
        setToken(token)
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

const kafkaAction = async (kafkaInput, token, setKafkaOutput) => {
    try {
        const response = await demoAxios.post('/02-kafka', { 'input': kafkaInput } , 
        { 
            headers: { 
              'Authorization': `Bearer ${token}` 
            }
        });
        setKafkaOutput(JSON.stringify(response.data))
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
    const [token, setToken] = useState('')

    const [kafkaInput, setKafkaInput] = useState('ABCDE')
    const [kafkaOutput, setKafkaOutput] = useState('')
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <div>Step 0: Login to get JWT token</div><br/>
                <div>Username: <input type="text" value={username} onChange={ e => setUsername(e.target.value)}></input></div><br/>
                <div>Password: <input type="password" value={password} onChange={ e => setPassword(e.target.value)}></input></div><br/>
                <input type="button" value="Login" onClick={ () => loginAction(username, password, setToken) }></input><br/>
                <div>JWT token <textarea value={token} cols="100" rows="6" readOnly/></div><br/>

                <hr style={{width: "100%"}}/>
                <div>Test 1: Call Kafka</div><br/> 
                <div>Messsage to send: <input type="text" value={kafkaInput} onChange={ e => setKafkaInput(e.target.value)}></input></div><br/>
                <input type="button" value="Call Kafka" onClick={ () => kafkaAction(kafkaInput, token, setKafkaOutput) }></input><br/>
                <div>Result: <textarea value={kafkaOutput} cols="100" rows="1" readOnly/></div><br/>
            </header>
        </div>
    );
}

export { LoginPage }