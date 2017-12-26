namespace java org.audit4j.microservice.transport.thrift


typedef i32 int;
typedef i64 long;
typedef string Timestamp;

struct TEventMeta {
  1: string client,
}

struct TField {
  1: string name,
  2: string value,
  3: string type,
}

typedef list<TField> Fields;

struct TAuditEvent	 {
  1: long uuid,
  2: Timestamp timestamp,
  3: string timestampFormat,
  4: optional TEventMeta meta,
  5: string action,
  6: optional string actor,
  7: optional string origin,
  8: optional string tag,
  9: optional string repository,
  10: optional Fields fields,
}

struct TAuditEventBatch {
  1. list<TAuditEvent> events
}

struct TAck {
  1: string message,
  2: int code
}

service TAuditService {
  TAck audit(1:TAuditEvent event),
}