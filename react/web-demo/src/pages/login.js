import React, { useState } from 'react'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
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
        <div className="page container-fluid p-5" style={{maxWidth: "700px"}}>

            <div className="card shadow mb-5">
                <div className="card-header"> 
                    <div>Step 0: Login</div>
                </div>
                <div className="card-body">
                    <div className="row">
                        <div className="col-4 py-2 text-end">Username: </div>
                        <div className="col-8 py-2"><Form.Control type="text" onChange={ e => setUsername(e.target.value)} value={username} /></div>
                        <div className="col-4 py-2 text-end">Password: </div>
                        <div className="col-8 py-2"><Form.Control type="password" onChange={ e => setPassword(e.target.value)} value={password} /></div>
                    </div>
                    <div className="d-flex justify-content-center mt-3"> 
                        <Button variant="primary" type="button" onClick={ () => loginAction(username, password, setToken) }>Login</Button>
                    </div>
                </div>
                <div className="card-footer">
                    <div><u>JWT Token</u></div>
                    <div style={{color:"green"}}>{token}</div>
                </div>
            </div>

            <div className="card shadow">
                <div className="card-header"> 
                    <div>Test 1: Send message to Kafka</div>
                </div>
                <div className="card-body">
                    <div className="row">
                        <div className="col-3 py-2 text-end">Message:</div> 
                        <div className="col-9 py-2"><Form.Control type="text" onChange={ e => setKafkaInput(e.target.value)} value={kafkaInput} /></div>
                    </div>
                    <div className="d-flex justify-content-center mt-3"> 
                        <Button variant="primary" type="button" onClick={ () => kafkaAction(kafkaInput, token, setKafkaOutput) }>Send to Kafka</Button>
                    </div>
                </div>
                <div className="card-footer">
                    <div><u>Result</u></div>
                    <div style={{color:"green"}}>{kafkaOutput}</div>
                </div>
            </div>


        </div>
    );
}

export { LoginPage }