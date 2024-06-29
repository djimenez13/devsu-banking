CREATE USER accounts_svc_user PASSWORD 'accounts_svc_pass';


DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS entities CASCADE;
DROP TABLE IF EXISTS snapshots CASCADE;
DROP TABLE IF EXISTS cdc_monitoring CASCADE;

CREATE TABLE events (
  event_id VARCHAR(1000) PRIMARY KEY,
  event_type VARCHAR(1000),
  event_data VARCHAR(1000) NOT NULL,
  entity_type VARCHAR(1000) NOT NULL,
  entity_id VARCHAR(1000) NOT NULL,
  triggering_event VARCHAR(1000),
  metadata VARCHAR(1000),
  published SMALLINT DEFAULT 0
);

CREATE INDEX events_idx ON events(entity_type, entity_id, event_id);
CREATE INDEX events_published_idx ON events(published, event_id);

CREATE TABLE entities (
  entity_type VARCHAR(1000),
  entity_id VARCHAR(1000),
  entity_version VARCHAR(1000) NOT NULL,
  PRIMARY KEY(entity_type, entity_id)
);

CREATE INDEX entities_idx ON entities(entity_type, entity_id);

CREATE TABLE snapshots (
  entity_type VARCHAR(1000),
  entity_id VARCHAR(1000),
  entity_version VARCHAR(1000),
  snapshot_type VARCHAR(1000) NOT NULL,
  snapshot_json VARCHAR(1000) NOT NULL,
  triggering_events VARCHAR(1000),
  PRIMARY KEY(entity_type, entity_id, entity_version)
);

CREATE TABLE cdc_monitoring (
  reader_id VARCHAR(1000) PRIMARY KEY,
  last_time BIGINT
);


DROP TABLE IF EXISTS message CASCADE;
DROP TABLE IF EXISTS received_messages CASCADE;

CREATE TABLE message (
  id VARCHAR(1000) PRIMARY KEY,
  destination TEXT NOT NULL,
  headers TEXT NOT NULL,
  payload TEXT NOT NULL,
  published SMALLINT DEFAULT 0,
  message_partition SMALLINT,
  creation_time BIGINT
);

CREATE INDEX message_published_idx ON message(published, id);

CREATE TABLE received_messages (
  consumer_id VARCHAR(1000),
  message_id VARCHAR(1000),
  creation_time BIGINT,
  published SMALLINT DEFAULT 0,
  PRIMARY KEY(consumer_id, message_id)
);

CREATE TABLE offset_store(
  client_name VARCHAR(255) NOT NULL PRIMARY KEY,
  serialized_offset VARCHAR(255)
);

DROP Table IF Exists saga_instance_participants;
DROP Table IF Exists saga_instance;
DROP Table IF Exists saga_lock_table;
DROP Table IF Exists saga_stash_table;

CREATE TABLE saga_instance_participants (
  saga_type VARCHAR(255) NOT NULL,
  saga_id VARCHAR(100) NOT NULL,
  destination VARCHAR(100) NOT NULL,
  resource VARCHAR(100) NOT NULL,
  PRIMARY KEY(saga_type, saga_id, destination, resource)
);


CREATE TABLE saga_instance(
  saga_type VARCHAR(255) NOT NULL,
  saga_id VARCHAR(100) NOT NULL,
  state_name VARCHAR(100) NOT NULL,
  last_request_id VARCHAR(100),
  end_state boolean,
  compensating boolean,
  failed boolean,
  saga_data_type VARCHAR(1000) NOT NULL,
  saga_data_json VARCHAR(1000) NOT NULL,
  PRIMARY KEY(saga_type, saga_id)
);

create table saga_lock_table(
  target VARCHAR(100) PRIMARY KEY,
  saga_type VARCHAR(255) NOT NULL,
  saga_Id VARCHAR(100) NOT NULL
);

create table saga_stash_table(
  message_id VARCHAR(100) PRIMARY KEY,
  target VARCHAR(100) NOT NULL,
  saga_type VARCHAR(255) NOT NULL,
  saga_id VARCHAR(100) NOT NULL,
  message_headers VARCHAR(1000) NOT NULL,
  message_payload VARCHAR(1000) NOT NULL
  );
  
  
GRANT ALL PRIVILEGES ON DATABASE "accounts" TO accounts_svc_user WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON SCHEMA "public" TO accounts_svc_user WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA "public" TO accounts_svc_user WITH GRANT OPTION;