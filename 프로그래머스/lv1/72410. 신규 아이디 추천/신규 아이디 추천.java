class Solution {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        new_id = new_id.replaceAll("\\.+", ".");
        new_id = new_id.replaceAll("^\\.", "");
        new_id = new_id.replaceAll("\\.$", "");
        new_id = new_id.length() == 0 ? "a" : new_id;
        new_id = new_id.length() >= 16 ? new_id.substring(0, 15) : new_id;
        new_id = new_id.replaceAll("\\.$", "");
        new_id = new_id.length() <= 2 ? new_id + new_id.charAt(new_id.length() - 1) : new_id;
        new_id = new_id.length() <= 2 ? new_id + new_id.charAt(new_id.length() - 1) : new_id;

        return new_id;
    }
}