import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Api from './api';
import Api2 from './api2';

function SecPage(){

  const [post1,setPost1] = useState({myPost:"hiiiiiiiii"})

    interface A {
        order:B,
        scheduled:string
        randomDay:string
      }
      
      interface B{
        orderId:number,
        location:string,
        gasStationId:number
      }

      const [values,setValues] = useState({
        gasStationId:""
      })

    const handleGasStationIdInputChange = (event:React.ChangeEvent<HTMLInputElement>) => {
        setValues({...values,gasStationId:event.target.value})
      }
      
      const [submitted,setSubmitted] = useState(false);
    const [data,setData] = useState({scheduled:"",orderId:0, location:"", gasStationId:0 ,randomDay:"" })

    const [post, setPost] = useState({scheduled:"",orderId:0, location:"", gasStationId:0 ,randomDay:"" });
    
console.log(post1.myPost)
    const handleSubmit = (event:React.FormEvent<HTMLFormElement>) =>{

      event.preventDefault();

      const getList =async () => {
        console.log("-----------" + values.gasStationId)
          const response = await Api2.get(`/${values.gasStationId}`);
          console.log(response);
          return response.data;
      }
        getList().
        then(value => {
          console.log("Printedd ", value)
          let maybeA =  value as A
          setData({...data,scheduled:maybeA.scheduled ,
            orderId:maybeA.order.orderId, 
            location:maybeA.order.location, 
            randomDay:maybeA.randomDay})
        })
        console.log("asdhas")
        setPost1({...post1,myPost:"hi"})
      
      setSubmitted(true);
      
    }

    return(
        <div>

            <form>
                
                <input
                onChange={handleGasStationIdInputChange}
                value={values.gasStationId}
                id="gasStationId"
                className='form-field'
                type="text"
                placeholder="Enter Gas Station Id"
                name='gasStationId'
                />
                
               
            </form>
            <form onSubmit={handleSubmit}>
                      {submitted ?<span>Please enter fuel type</span>:null}
                      <button className='form-field' type='submit'>
                        Search
                      </button>

                </form>

          <table border={2} cellPadding={5}>
            <thead>
            <tr>
              <th>Order Id</th>
              <th>location </th>
              <th>Status</th>
              <th>Scheduled Date</th>
            </tr>
            <tr>
              <td>{data.orderId}</td>
              <td>{data.location}</td>
              <td>{data.scheduled}</td>
              <td>{data.randomDay}</td>
          
            </tr>
            </thead>
          </table>
      </div>
    )
}


export default SecPage;
