import React, { useEffect, useState } from 'react';
import './App.css';
import Api from './common/api';
import axios from 'axios';

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

  const [submitted,setSubmitted] = useState(false);
  const [data,setData] = useState({randomDay:"",orderId:0, location:"", gasStationId:0  })
  
  const getList =async () => {
    const response = await Api.get('/');
    console.log(response);
    return response.data;
  }
  useEffect(() => {
    getList().
    then(value => {
      console.log("Printedd ", value)
      let maybeA =  value as A[]
      setData({...data,randomDay:maybeA[0].randomDay ,
        orderId:maybeA[0].order.orderId, 
        location:maybeA[0].order.location, 
        gasStationId:maybeA[0].order.gasStationId})
    })
  },[]);

  const handleSubmit = (event:React.FormEvent<HTMLFormElement>) =>{
    axios({
      method: 'put',
      url: `http://localhost:8391/dispatching/dispatchById/${data.gasStationId}`,
      // data: { ${data.gasStationId}
      //   gasStationId: data.gasStationId
      // }
    });
    setSubmitted(true);
  }

  return (
    <div className="App">

      <table border={2} cellPadding={5}>
        <tr>
          <th>Order Id</th>
          <th>location </th>
          <th>Gas Station Id</th>
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

    </div>
  );
}

export default App;
