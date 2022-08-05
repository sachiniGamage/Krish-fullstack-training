import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import Api from './common/api';
import { getValue } from '@testing-library/user-event/dist/utils';
import axios from 'axios';
import { stringify } from 'querystring';

interface A {
  order:B,
  randomDay:string
  
}

interface B{
  orderId:number,
  location:string,
  gasStationId:number
}

function App() {

  const testing = [{number: "1. ", other: {name:"some data",name2:"some data" }},
  {number: "2. ", other: {name:"some data",name2:"some data" }}]

  const [submitted,setSubmitted] = useState(false);
  const [data,setData] = useState({randomDay:"",orderId:0, location:"", gasStationId:0  })
  
  const getList =async () => {
    const response = await Api.get('/');
    console.log(response);
    return response.data;
  }
  // let my= [{randomDay:data.randomDay, orderId:data.orderId,gasStationId:data.gasStationId, location:data.location}];
  useEffect(() => {
    getList().
    then(value => {
      console.log("Printedd ", value)
      let maybeA =  value as A
      setData({...data,randomDay:maybeA.randomDay ,
        orderId:maybeA.order.orderId, 
        location:maybeA.order.location, 
        gasStationId:maybeA.order.gasStationId})
        // my = [{my,}]
    })
  },[]);

  const handleSubmit = (event:React.FormEvent<HTMLFormElement>) =>{
    axios({
      method: 'put',
      url: 'http://localhost:8391/dispatching/dispatchById/${data.gasStationId}',
      // data: {
      //   gasStationId: data.gasStationId
      // }
    });
    setSubmitted(true);
  }

  return (
    <div className="App">

      <table border={2} cellPadding={5}>
        <tr>
          <th>OrderId</th>
          <th>location</th>
          <th>Gas StationId</th>
          <th>Scheduled Date</th>
          <th>Dispatch</th>
        </tr>
        <tr>
          <td>{data.orderId}</td>
          <td>{data.location}</td>
          <td>{data.gasStationId}</td>
          <td>{data.randomDay}</td>
          
          <td><form onSubmit={handleSubmit}>
            {submitted ?<span>Please enter fuel type</span>:null}
            <button className='form-field' type='submit'>
              Dispatch
            </button>
          </form></td>
        </tr>
      </table>

      {/* {
        my.map(mylist =>{

        })
      } */}

      {
        testing.map(list => (
          <li>{list.number}{testing.map(item =>(
            <span>{item.other.name}</span>
          ))}</li>
          
        ))
      }

      
      {/* {
        Object.keys(sampleJSON.object).map((key, i) => (
          <p key={i}>
            <span>Key Name: {key}</span>
            <span>Value: {sampleJSON.object[key]}</span>
          </p>
        )
      } */}
    </div>
  );
}

export default App;
