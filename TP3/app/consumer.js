const amqp = require("amqplib");

async function startConsumer() {

  const conn = await amqp.connect("amqp://admin:admin@rabbitmq");
  const channel = await conn.createChannel();

  const QUEUE = "commandes";

  await channel.assertQueue(QUEUE, { durable: true });

  console.log("Waiting for messages...");

  channel.consume(QUEUE, (msg) => {

    const commande = JSON.parse(msg.content.toString());

    console.log("New order received:", commande);

    channel.ack(msg);

  });

}

setTimeout(startConsumer, 5000);