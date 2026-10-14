package com.ragaroads.models;

public class AllocationResult {

    private String status;
    private String reason;

    public AllocationResult(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public static AllocationResult confirmed(String reason) {
        return new AllocationResult("Confirmed", reason);
    }

    public static AllocationResult rejected(String reason) {
        return new AllocationResult("Rejected", reason);
    }

    public boolean isConfirmed() {
        return "Confirmed".equals(status);
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
