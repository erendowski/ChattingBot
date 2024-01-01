public class Message {


        // Creating variables
        private String role;
        private String content;


        // Message func for conversation
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {

            return role;
        }

        public String getContent() {

            return content;
        }
}

