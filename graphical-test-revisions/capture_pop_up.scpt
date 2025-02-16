tell application "LTspice"
	activate
end tell

-- Open the FullAdder_Test.asc file in LTspice
do shell script "open -a /Applications/LTspice.app /Users/ryanoates/week4/graphical-test-revisions/FullAdder_Test.asc"

-- Wait for LTspice to load the file and for any pop-up to settle
delay 10

tell application "System Events"
	tell process "LTspice"
		set targetWindow to missing value
		repeat with win in windows
			if (name of win contains "Aborting:") or (name of win contains "Alert") then
				set targetWindow to win
				exit repeat
			end if
		end repeat
			
		if targetWindow is not missing value then
			set theID to id of targetWindow
			do shell script "screencapture -x -l " & theID & " /Users/ryanoates/week4/graphical-test-revisions/LTspice_ErrorLog.png"
		else
			set targetWindow to front window
			set theID to id of targetWindow
			do shell script "screencapture -x -l " & theID & " /Users/ryanoates/week4/graphical-test-revisions/LTspice_Main.png"
		end if
	end tell
end tell

return "Automation script finished."