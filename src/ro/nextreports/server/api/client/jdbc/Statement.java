/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.nextreports.server.api.client.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

import ro.nextreports.server.api.client.StatementWebServiceClient;
import ro.nextreports.server.api.client.WebServiceClient;
import ro.nextreports.server.api.client.WebServiceException;

/**
 * @author Decebal Suiu
 */
public class Statement implements java.sql.Statement {

	protected String id;
	protected StatementWebServiceClient webServiceClient;

	public Statement(String id, WebServiceClient webServiceClient) {
		this.id = id;
		this.webServiceClient = new StatementWebServiceClient(webServiceClient);
	}

	@Override
    public void close() throws SQLException {
		try {
			webServiceClient.close(id);
		} catch (WebServiceException e) {
			// TODO
			e.printStackTrace();
		}
	}

	@Override
    public ResultSet executeQuery(String sql) throws SQLException {
		try {
			return webServiceClient.executeQuery(id, sql);
		} catch (WebServiceException e) {
			// TODO
			e.printStackTrace();
			return null;
		}
	}

	@Override
    public void setQueryTimeout(int seconds) throws SQLException {
		try {
			webServiceClient.setQueryTimeout(id, seconds);
		} catch (WebServiceException e) {
			// TODO
			e.printStackTrace();
		}
	}

	@Override
    public void setMaxRows(int max) throws SQLException {
		try {
			webServiceClient.setMaxRows(id, max);
		} catch (WebServiceException e) {
			// TODO
			e.printStackTrace();
		}
	}

	@Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int executeUpdate(String sql) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getMaxFieldSize() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void setMaxFieldSize(int max) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getMaxRows() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getQueryTimeout() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void cancel() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public SQLWarning getWarnings() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void clearWarnings() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void setCursorName(String name) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean execute(String sql) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public ResultSet getResultSet() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getUpdateCount() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean getMoreResults() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void setFetchDirection(int direction) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getFetchDirection() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void setFetchSize(int rows) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getFetchSize() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getResultSetConcurrency() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getResultSetType() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void addBatch(String sql) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void clearBatch() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int[] executeBatch() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public Connection getConnection() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean getMoreResults(int current) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public ResultSet getGeneratedKeys() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public int getResultSetHoldability() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean isClosed() throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public void setPoolable(boolean poolable) throws SQLException {
		throw new NotImplementedException();
	}

	@Override
    public boolean isPoolable() throws SQLException {
		throw new NotImplementedException();
	}

    @Override
    public void closeOnCompletion() throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        throw new NotImplementedException();
    }

}
