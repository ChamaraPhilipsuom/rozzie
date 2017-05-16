package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.UUID;

@NodeEntity
public class FlightDAO {

    @GraphId
    private Long nodeId;
    private UUID flightId;

    @Relationship(type="SOURCE_PORT", direction = Relationship.OUTGOING)
    private FlightDAO sourcePort;

    @Relationship(type="DEST_PORT", direction = Relationship.OUTGOING)
    private FlightDAO destinationPort;

    public FlightDAO(){}

    public Long getNodeId() {
        return nodeId;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public FlightDAO getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(FlightDAO sourcePort) {
        this.sourcePort = sourcePort;
    }

    public FlightDAO getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(FlightDAO destinationPort) {
        this.destinationPort = destinationPort;
    }
}
