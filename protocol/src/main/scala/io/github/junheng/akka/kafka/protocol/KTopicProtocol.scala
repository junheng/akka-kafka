package io.github.junheng.akka.kafka.protocol

import org.apache.commons.codec.digest.DigestUtils

object KTopicProtocol {

  case class GetGroup(group: String, pullerCount: Int)

  /**
    * provide a key and content to produce a message
    * if no key provide will generated with md5 on client
    *
    * @param key     message key
    * @param content message content
    */
  case class Payload(key: Array[Byte], content: Array[Byte]) extends KafkaPayloadMessage

  object Payload {
    def apply(content: Array[Byte]): Payload = Payload(DigestUtils.md5(content), content)
  }

  case class BatchPayload(payloads: List[Payload]) extends KafkaPayloadMessage
}
