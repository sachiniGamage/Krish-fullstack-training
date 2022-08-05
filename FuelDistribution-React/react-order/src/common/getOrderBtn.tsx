import React, { useEffect, useState } from 'react';

function GetOrderBtn(){

    const [values,setValues] = useState({
        gasStationId:"",
      })

    const handleGasStationIdInputChange = (event:React.ChangeEvent<HTMLInputElement>) => {
        setValues({...values,gasStationId:event.target.value})
      }

    return(
        <div>
            <table>
                <tr>
                <th>
                <input
                onChange={handleGasStationIdInputChange}
                value={values.gasStationId}
                id="gasStationId"
                className='form-field'
                type="text"
                placeholder="Enter Gas Station Id"
                name='gasStationId'
                />
                </th>
                <th><button>Search</button></th>
                </tr>
            </table>
        </div>
        
    )
}

export default GetOrderBtn;