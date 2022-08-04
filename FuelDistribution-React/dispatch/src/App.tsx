import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import Api from './common/api';

interface A {
  name: string,
  id:number
}

function App() {

  const [data,setData] = useState({name:"", id:0})

  const getList =async () => {
    const response = await Api.get('/');
    console.log(response);
    return response.data;
  }
  useEffect(() => {
    getList().then(value => {
      console.log("Printedd ", value)
      let maybeA =  value as A
      setData({...data, name:maybeA.name,id:maybeA.id })
    })
  },[]);
  return (
    <div className="App">
      <h1>{data.name}</h1>
      <h1>{data.id}</h1>
    </div>
  );
}

export default App;
