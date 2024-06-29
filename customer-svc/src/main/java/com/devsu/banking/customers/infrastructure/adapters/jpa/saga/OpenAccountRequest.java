package com.devsu.banking.customers.infrastructure.adapters.jpa.saga;

import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "open_account_requests")
public class OpenAccountRequest {
	
	@Id
	private String id;
	
	@Enumerated(EnumType.STRING)
	private OpenAccountState state;
	
	@Embedded
	private OpenAccountDetails details;
	
	private OpenAccountRejectionReason rejectionReason;
	
	
	public OpenAccountRequest(OpenAccountDetails details) {
		this.details = details;
		this.state = OpenAccountState.PENDING;
	}
	
	
	public static OpenAccountRequest createRequest(OpenAccountDetails details) {
		return new OpenAccountRequest(details);
	}
	
	public void approve() {
		this.state = OpenAccountState.APPROVED;
	}
	
	public void reject(OpenAccountRejectionReason rejectionReason) {
		this.state = OpenAccountState.REJECTED;
		this.rejectionReason = rejectionReason;
	}
	
	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
	}

}
