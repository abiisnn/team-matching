#include <bits/stdc++.h>

using namespace std;

int main(){
	bool reference; cin >> reference;
	string ref_personality;
	if(reference) cin >> ref_personality;
	int n, size; cin >> n >> size;

	vector< pair<int,int> > group_points(n);
	vector< set<string> > group_members(n);
	map<int,string> group_personalities;

	for(int id=0; id<n; id++){
		map<string,string> people;
		string username, personality;

		int group_personality[4][2];
		for(int i=0; i<4; i++){
			group_personality[i][0] = 0;
			group_personality[i][1] = 0;
		}

		for(int i=0; i<size; i++){
			cin >> username >> personality;
			group_members[id].insert(username);
			people[username] = personality;
			if(personality[0] == 'I')
				group_personality[0][0]++;
			else
				group_personality[0][1]++;

			if(personality[1] == 'N')
				group_personality[1][0]++;
			else
				group_personality[1][1]++;

			if(personality[2] == 'T')
				group_personality[2][0]++;
			else
				group_personality[2][1]++;

			if(personality[3] == 'J')
				group_personality[3][0]++;
			else
				group_personality[3][1]++;
		}

		if(reference){
			if(ref_personality[0] == 'I')
				group_points[id].first += group_personality[0][0];
			else
				group_points[id].first += group_personality[0][1];

			if(ref_personality[1] == 'N')
				group_points[id].first += group_personality[1][0];
			else
				group_points[id].first += group_personality[1][1];

			if(ref_personality[2] == 'T')
				group_points[id].first += group_personality[2][0];
			else
				group_points[id].first += group_personality[2][1];

			if(ref_personality[3] == 'J')
				group_points[id].first += group_personality[3][0];
			else
				group_points[id].first += group_personality[3][1];

			group_personalities[id] = ref_personality;
		}else{
			string average;
			group_points[id].second = id;

			if(group_personality[0][0] >= group_personality[0][1]){
				average.push_back('I');
				group_points[id].first += group_personality[0][0];
			}
			else{
				average.push_back('E');
				group_points[id].first += group_personality[0][1];
			}

			if(group_personality[1][0] >= group_personality[1][1]){
				group_points[id].first += group_personality[1][0];
				average.push_back('N');
			}
			else{
				group_points[id].first += group_personality[1][1];
				average.push_back('S');
			}

			if(group_personality[2][0] >= group_personality[2][1]){
				group_points[id].first += group_personality[2][0];
				average.push_back('T');
			}
			else{
				group_points[id].first += group_personality[2][1];
				average.push_back('F');
			}

			if(group_personality[3][0] >= group_personality[3][1]){
				group_points[id].first += group_personality[3][0];
				average.push_back('J');
			}
			else{
				group_points[id].first += group_personality[3][1];
				average.push_back('P');
			}

			group_personalities[id] = average;

		}

	}

	sort(group_points.rbegin(), group_points.rend());

	cout << "Best Group: ";
	for(auto s:group_members[group_points[0].second])
		cout << s << " ";
	cout << endl;
	cout << "Group Personality = " << group_personalities[group_points[0].second] << endl;

}
