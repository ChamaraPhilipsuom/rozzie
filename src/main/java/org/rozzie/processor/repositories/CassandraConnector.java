package org.rozzie.processor.repositories;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import org.springframework.stereotype.Component;

/**
 * Class used for connecting to Cassandra database.
 */
@Component("cassandraConnector")
public class CassandraConnector
{
    /** Cassandra Cluster. */
    private Cluster cluster;
    /** Cassandra Session. */
    private Session session;
    /**
     * Connect to Cassandra Cluster specified by provided node IP
     * address and port number.
     *
     * @param node Cluster node IP address.
     * @param port Port of cluster host.
     */
    public void connect(final String node, final int port)
    {
        this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();
        final Metadata metadata = cluster.getMetadata();
        for (final Host host : metadata.getAllHosts())
        {
            System.out.println("Datacenter: " +host.getDatacenter() +"; Host: " + host.getAddress() + "; Rack: "
                    + host.getRack() + "\n");
        }
        session = cluster.connect();
    }
    /**
     * Provide my Session.
     *
     * @return My session.
     */
    public Session getSession()
    {
        return this.session;
    }
    /** Close cluster. */
    public void close()
    {
        cluster.close();
    }

	public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor) {
		StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append(keyspaceName)
				.append(" WITH replication = {").append("'class':'").append(replicationStrategy)
				.append("','replication_factor':").append(replicationFactor).append("};");

		String query = sb.toString();
		session.execute(query);
	}
}