const { Kafka } = require('kafkajs')
const controller =  require('./dispatchController')
const services = require('./db_connection');
const kafka = new Kafka({
//   clientId: 'consumer-groupId-1',
  brokers: ['192.168.8.102:9092']
})

// controller.gett;

const producer = kafka.producer()
const consumer = kafka.consumer({ groupId: 'groupId18' })

// controller.gett().toString();
// console.log("my msg ------" +services.f1().toString())

// const a = services.f1().toString();

const run = async () => {
    // if(controller.save()){
        // Producing
        await producer.connect()
        await producer.send({
            topic: 'OrderDispatched',
            messages: [
            { value: 'Hello KafkaJS user!' },
            ],
        })
    // }
  // Consuming
  await consumer.connect()
  await consumer.subscribe({ topic: 'DeliveryScheduled', fromBeginning: true })

  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      console.log({
        partition,
        offset: message.offset,
        value: message.value.toString(),
      })
    },
    
  })
}

run().catch(console.error)