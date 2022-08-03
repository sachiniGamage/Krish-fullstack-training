import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [values,setValues] = useState({
    ownerName:"",
    location:"",
    gasStationId:"",
  })

  const fuelType = React.useRef<HTMLSelectElement>(null);
  const fuelCapacity = React.useRef<HTMLSelectElement>(null);
  

  const [submitted,setSubmitted] = useState(false);
  const [valid, setValid] = useState(false);
  const [state, setState] = useState({fuelType:"" });
  const [capacity,setCapacity] = useState({fuelCapacity:""})
  const axios = require('axios').default;

  //ownerName
  const handleOwnerNameInputChange = (event:React.ChangeEvent<HTMLInputElement>) => {
    setValues({...values,ownerName:event.target.value})
  }

  //location
  const handleLocationInputChange = (event:React.ChangeEvent<HTMLInputElement>) => {
    setValues({...values,location:event.target.value})
  }

  //gasStationId
  const handleGasStationIdInputChange = (event:React.ChangeEvent<HTMLInputElement>) => {
    setValues({...values,gasStationId:event.target.value})
  }

  //fuelDropdown
  const handleFuelDropDownInputChange = (event:React.ChangeEvent<HTMLSelectElement>) =>{
    setState({ ...state,fuelType:event.target.value.trim()})
  }

  //capacitydropdown
  const handleCapacityDropDownInputChange = (event:React.ChangeEvent<HTMLSelectElement>) =>{
    setCapacity({ ...capacity,fuelCapacity:event.target.value.trim()})
  }

  //handle submit
  const handleSubmit = (event:React.FormEvent<HTMLFormElement>) =>{
   if(values.ownerName && values.location && values.gasStationId && capacity.fuelCapacity && state.fuelType){
    setValid(true);
   }

   axios({
    method: 'post',
    url: 'http://localhost:8388/orderService/save',
    data: {
      cpcOwnerName: values.ownerName,
      location: values.location,
      gasStationId: values.gasStationId,
      fuelCapacity: capacity.fuelCapacity,
      fuelType: state.fuelType
    }
  });

    setSubmitted(true);
    
  }


  return (
    <div className='form-container'>
        <form className='order-form' onSubmit={handleSubmit}>
          {submitted && valid ?<div className='success-message'>Success!!!</div> : null}
          {/* ownerName */}
          <input
          onChange={handleOwnerNameInputChange}
          value={values.ownerName}
            id="ownerName"
            className='form-field'
            type="text"
            placeholder="Owner's Name"
            name='ownerName'
            />
            {submitted && !values.ownerName ?<span>Please enter owner's name</span>:null}
            {/* location */}
          <input
          onChange={handleLocationInputChange}
          value={values.location}
            id="location"
            className='form-field'
            type="text"
            placeholder="location"
            name='location'
            />
            {submitted && !values.location?<span>Please enter location</span>:null}
            {/* gasStationId */}
          <input
          onChange={handleGasStationIdInputChange}
          value={values.gasStationId}
            id="gasStationId"
            className='form-field'
            type="text"
            placeholder="gasStationId"
            name='gasStationId'
            />
            {submitted && !values.gasStationId?<span>Please enter gas station id</span>:null}
            {/* fuelCapacity */}
         {/* fuelCapacity */}
         <select ref={fuelCapacity} className='form-field' name='fuelCapacity' value={capacity.fuelCapacity} onChange={handleCapacityDropDownInputChange} >
              <option >Fuel Capacity</option>
              <option  value={33000}>33000</option>
              <option value={66000}>66000</option>
              <option value={13000}>13000</option>
            </select>
            {submitted && !capacity.fuelCapacity?<span>Please enter fuel capacity</span>:null}


            {/* fuelType */}
            <select ref={fuelType} className='form-field' name='fuel' value={state.fuelType} onChange={handleFuelDropDownInputChange} >
              <option >Fuel</option>
              <option  value={"Patrol92"}>Petrol 92</option>
              <option value={"Patrol95"}>Petrol 95</option>
              <option value={"Diesel"}>Diesel</option>
              <option value={"SuperDiesel"}>Super Diesel</option>
            </select>

            {submitted && !state.fuelType?<span>Please enter fuel type</span>:null}
            <button className='form-field' type='submit'>
              Submit
            </button>
        </form>
    </div>
  );
}

export default App;
