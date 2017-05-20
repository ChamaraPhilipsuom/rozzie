package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.rozzie.processor.models.Flight;

import java.util.UUID;

@NodeEntity
public class FlightDAO {

    @GraphId
    private Long nodeId;
    private String flightId;

    @Relationship(type="SOURCE_PORT", direction = Relationship.OUTGOING)
    private AirportDAO sourcePort;

    @Relationship(type="DEST_PORT", direction = Relationship.OUTGOING)
    private AirportDAO destinationPort;

    public FlightDAO(){}
    public FlightDAO(UUID flightId){
        this.flightId = flightId.toString();
    }

    public Long getNodeId() {
        return nodeId;
    }

    public UUID getFlightId() {
        return UUID.fromString(flightId);
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId.toString();
    }

    public AirportDAO getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(AirportDAO sourcePort) {
        this.sourcePort = sourcePort;
    }

    public AirportDAO getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(AirportDAO destinationPort) {
        this.destinationPort = destinationPort;
    }
}
