const mysql = require('mysql');
const sequelize = require('sequelize');

const con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password:"",
    database: "FuelOrders2"
});

con.connect(function(err) {
    if(err) throw err;
    console.log("connected");
});

this.f1 = function getAllScheduledOrders(){
    return con.query("SELECT * FROM schedule",function(err, result, fields) {
     if (err) throw err;
     console.log(result);
   });
 }

 this.f2 = function saveDispatched(){
     if(con.query(`select * from schedule where scheduled = 3`)){
         con.query(`update schedule set scheduled = 5 `,function(err, result, fields) {
             if (err) throw err;
             console.log(result);
           });
     }
 }

// function getAllScheduledOrders(){
//     return con.
// }