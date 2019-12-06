#include "mongoose.h"
#include <bits/stdc++.h>
using namespace std;

static const char *s_http_port = "8000";
static struct mg_serve_http_opts s_http_server_opts;

vector<string> split(string str, char token) {
  stringstream ss(str);
  vector<string> v;
  while (getline(ss, str, token)) v.push_back(str);
  return v;
}

string getMatchingResponse(vector<vector<int>> &skills) {

  vector<vector<string>> ans = {
    {"searleser97", "abiisnn", "edward"},
    {"marquess", "Ulises", "Daniel"}
  };

  string response = "{\"groups\":[";
  for (int i = 0; i < ans.size(); i++) {
    if (i) response += ",";
    response += "[";
    for (int j = 0; j < ans[i].size(); j++) {
      if (j) response += ",";
      response += "\"" + ans[i][j] + "\"";
    }
    response += "]";
  }
  response += "]}";
  return response;
}

void sendResponse(struct mg_connection *connection, string data) {
  mg_printf(connection, "%s",
                "HTTP/1.1 200 OK\r\n"
                "Access-Control-Allow-Origin: *\r\n"
                "Content-Type: application/json\r\n"
                "Transfer-Encoding: chunked\r\n\r\n");
  mg_printf_http_chunk(connection, data.c_str());
  mg_send_http_chunk(connection, "", 0);
}

struct User {
  string username;
  string name;
  vector<int> skills;
};

unordered_map<int, vector<User>> db;

static void ev_handler(struct mg_connection *connection, int event_id,
                       void *event_data) {
  struct http_message *http = (struct http_message *)event_data;

  std::string uri("");

  if (event_id == MG_EV_HTTP_REQUEST) {
    connection->flags |= MG_F_SEND_AND_CLOSE;
    uri.assign(http->message.p, http->message.len);
    uri = split(uri.substr(0, uri.find("\r\n")), ' ')[1];
    cout << uri << endl;
    vector<string> params = split(uri, '?');
    string endpoint = params[0];

    // ip:8000/save?groupId-username-name-skill1,skill2,...-personality-%,%,%,%

    if (endpoint == "/save") {

      vector<string> data = split(params[1], '-');
      int groupId = stoi(data[0]);
      string username = data[1];
      string name = data[2];
      vector<string> skillsString = split(data[3], ',');
      string personality = data[4];
      vector<string> percentages = split(data[5], ',');
      vector<int> skills(skillsString.size());
      
      for (int i = 0; i < skillsString.size(); i++) {
        skills[i] = stoi(skillsString[i]);
      }

      User userData;
      userData.skills = skills;
      userData.username = username;
      userData.name = name;
      db[groupId].push_back(userData);

      sendResponse(connection, "OK");

    } else if (endpoint == "/getMatchings") {

      int groupId = stoi(params[1]);
      int limit = db[groupId].size();
      vector<User> group = db[groupId];
      vector<vector<int>> skills(limit);

      for (int i = 0; i < limit; i++) {
        skills[i] = group[i].skills;
      }

      string ans = getMatchingResponse(skills);

      sendResponse(connection, ans);
    } else {
      mg_serve_http(connection, http, s_http_server_opts);
    }
  }
}

int main() {
  struct mg_mgr mgr;
  struct mg_connection *nc;

  mg_mgr_init(&mgr, NULL);
  printf("Starting web server on port %s\n", s_http_port);
  nc = mg_bind(&mgr, s_http_port, ev_handler);
  if (nc == NULL) {
    printf("Failed to create listener\n");
    return 1;
  }

  // Set up HTTP server parameters
  mg_set_protocol_http_websocket(nc);
  s_http_server_opts.document_root = "."; // Serve directory
  s_http_server_opts.enable_directory_listing = "yes";
  while (true) {
    mg_mgr_poll(&mgr, 1000);
  }
  mg_mgr_free(&mgr);

  return 0;
}
